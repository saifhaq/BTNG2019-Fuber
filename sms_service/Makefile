deploy:
	gcloud --quiet functions deploy sms --runtime python37 --trigger-http --entry-point service_function --env-vars-file env.yaml --region europe-west1
