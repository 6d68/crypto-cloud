FROM nginx

COPY nginx-site.conf /etc/nginx/conf.d/default.conf
COPY build /usr/share/nginx/html
COPY run.sh /usr/sbin/run

RUN chmod u+x /usr/sbin/run

CMD [ "run" ]
