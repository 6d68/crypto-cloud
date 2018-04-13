FROM nginx

# remove default conf
RUN rm /etc/nginx/conf.d/default.conf

# add app build to static sites
COPY build /usr/share/nginx/html

# copy conf template
COPY nginx-site.conf /etc/nginx/conf.d/site.conf.template

# add api gateway environment variable with default value
ENV APP_API_GATEWAY="http://172.19.0.1:5555" 
 
COPY run.sh /usr/sbin/run

RUN chmod u+x /usr/sbin/run

CMD [ "run" ]
