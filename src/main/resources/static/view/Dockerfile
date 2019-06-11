FROM node:latest

RUN mkdir -p /reactapp

WORKDIR /reactapp

ADD src/main/resources/static/view/package.json /reactapp/package.json
ADD src/main/resources/static/view/public /reactapp/public
ADD src/main/resources/static/view/node_modules /reactapp/node_modules
ADD src/main/resources/static/view/src /reactapp/src

ENTRYPOINT ["npm", "start"]