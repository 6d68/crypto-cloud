#!/bin/sh

echo "APP_API_GATEWAY": $APP_API_GATEWAY

# set config according to env variable
envsubst '$APP_API_GATEWAY' < /etc/nginx/conf.d/site.conf.template > /etc/nginx/conf.d/default.conf

# start nginx
nginx -g 'daemon off;'
exec "$@"
