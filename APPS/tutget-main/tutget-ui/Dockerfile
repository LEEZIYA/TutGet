FROM node:alpine AS builder
WORKDIR /usr/local/app
COPY ./ /usr/local/app/
RUN npm install && \
    npm run build-prod
FROM nginx:alpine
COPY --from=builder /usr/local/app/dist/* /usr/share/nginx/html/
COPY /nginx.conf /etc/nginx/conf.d/default.conf