# Dockerfile for React application

# Use official Node.js image as base image
FROM node:14-alpine as build

# Set working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json (if available)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire application
COPY . .

# Build React app
RUN npm run build

# Use nginx as base image for serving static content
FROM nginx:alpine

# Copy build output from previous stage into nginx directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80 to outside world
EXPOSE 80

# Start nginx server
CMD ["nginx", "-g", "daemon off;"]
