from flask import Flask, request, session
from twilio.twiml.messaging_response import MessagingResponse
from datetime import datetime, timedelta
import requests

app = Flask(__name__) # pylint: disable=invalid-name
app.secret_key = b"%$\xf9\xbd\xb1\xf3\x17,\x81]\x1f\xd7\xc3v\x03\xe57&q\x9e\x99F\x19\xa1"

TEMPLATE = '''
{question}
----
{options}
'''

SHITTY_ITEMS_LIST = {
    'types': [
        'tractor',
        'combine harvester',
        'fertilizer',
        'unpain intern',
    ]
}

def get_items_list():
    # res = requests.get('%s/assets/available_types_to_rent')
    # types = res.json()['types']
    types = SHITTY_ITEMS_LIST['types']

    return {k + 1: v for k, v in enumerate(types)}

def get_view(question, options):
    if options:
        options[0] = 'Quit'
        options_str = '\n'.join(['%s: %s' % (k, v) for k, v in options.items()])
    else:
        options_str = ''
    return TEMPLATE.format(question=question, options=options_str)

def checkout():
    session['cart'] = []
    session['stage'] = 0
    return get_view('Delivery scheduled', None)

def view_cart_answer(selection_number):
    if selection_number == 1:
        session['stage'] = 2
        return add_items_to_cart_question()
    return checkout()

def view_cart_question():
    options = {
        1: 'Add new item'
    }
    if session.get('cart'):
        options[2] = 'Checkout'

        question = 'Cart:\n'.join(['%s: %s - %s' % (x['name'], x['start'], x['end'])
                                   for x in session['cart']])
    else:
        question = ''
    session['stage'] = 1
    return get_view(question, options)

def _get_available_days():
    today = datetime.today()
    available_days = {i: (today + timedelta(days=1 * i)).strftime('%Y-%m-%d')
                      for i in range(1, 5)}
    return available_days

def choose_start_date_answer(selection):
    available_days = _get_available_days()
    start_date = available_days[selection]
    end_date = (datetime.strptime(start_date, '%Y-%m-%d') + timedelta(days=session['current_time_period'])).strftime('%Y-%m-%d')
    item = {
        'name': session['current_item'],
        'start': start_date,
        'end': end_date,
    }
    session['cart'].append(item)
    session['stage'] = 0
    return view_cart_question()

def choose_start_date_question():
    available_days = _get_available_days()
    session['stage'] = 7
    return get_view('Choose delivery date', available_days)

def choose_time_period_answer(days_to_rent):
    session['current_time_period'] = days_to_rent
    session['stage'] = 6
    return choose_start_date_question()

def choose_time_period_question():
    options = ''
    question = 'How many days would you like to rent?'
    session['stage'] = 5
    return get_view(question, options)

def add_items_to_cart_answer(selection_number):
    avaiable_items = get_items_list()
    session['current_item'] = avaiable_items[selection_number]
    session['stage'] = 4
    return choose_time_period_question()

def add_items_to_cart_question():
    options = get_items_list()
    question = 'Please select item to add'
    session['stage'] = 3
    return get_view(question, options)

OPTIONS = {
    0: view_cart_question,
    1: view_cart_answer,
    2: add_items_to_cart_question,
    3: add_items_to_cart_answer,
    4: choose_time_period_question,
    5: choose_time_period_answer,
    6: choose_start_date_question,
    7: choose_start_date_answer,
}

@app.route('/sms', methods=['GET', 'POST'])
def handle_convo():
    stage = session.get('stage')
    resp = MessagingResponse()
    if not stage:
        msg = view_cart_question()
    else:
        if session.get('cart') is None:
            session['cart'] = []
        message = request.form.get('Body')
        selection = int(message)
        if selection == 0:
            checkout()
        msg = OPTIONS[stage](selection)
    resp.message(msg)
    print(session.get('cart'))
    return str(resp)
