FROM nginx:alpine

# Correct path from project root
COPY frontend/ /usr/share/nginx/html/

COPY docker/frontend/nginx.conf /etc/nginx/conf.d/default.conf
