from flask import Response
from btng.settings import AUTH_TOKEN, ACCOUNT_SID, TWILLIO_TRIAL_NUMBER
from btng.endpoints import app

PATH_BASE = '/execute'
SERVICE_BASE = '/sms'

def service_function(request):
    return Response.from_app(app, sanitise_environ(request.environ))

def sanitise_environ(environ):
    environ['PATH_INFO'] = environ['PATH_INFO'].replace(PATH_BASE, SERVICE_BASE) or SERVICE_BASE
    environ['SERVER_PROTOCOL'] = 'HTTP/1.1'
    return environ

if __name__ == "__main__":
    app.run('127.0.0.1', 5000, debug=True)
