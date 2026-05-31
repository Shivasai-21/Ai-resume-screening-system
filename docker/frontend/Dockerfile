FROM nginx:alpine

# Copy frontend files from project root
COPY frontend /usr/share/nginx/html

# Replace default site config
COPY docker/frontend/nginx.conf /etc/nginx/conf.d/default.conf

