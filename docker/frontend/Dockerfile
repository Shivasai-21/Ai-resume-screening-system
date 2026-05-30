FROM nginx:alpine

# Copy static frontend files (HTML, CSS, JS, assets)
COPY . /usr/share/nginx/html

# Replace default site config with our own
COPY nginx.conf /etc/nginx/conf.d/default.conf

