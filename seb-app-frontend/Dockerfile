FROM node:alpine AS builder
COPY . ./app
WORKDIR /app
RUN npm i
RUN $(npm bin)/ng build --prod

FROM nginx
COPY --from=builder /app/dist/seb-app-angular/ /usr/share/nginx/html
COPY nginx/nginx.conf /etc/nginx/nginx.conf
