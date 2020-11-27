# SEB Product Recommendations Service

**An easy way to get recommendations regarding SEB products**

This is a simple application, which ties together Spring Boot, Angular and Docker to provide smooth and
consistent experience with neat user interface.

#### Backend Service
Provides management and backend validation for both users and products.

| Method | Path                                                  | Description                           | User role       |
|--------|-------------------------------------------------------|---------------------------------------|-----------------|
| POST   | /api/auth/signin                                      | Authenticate user                     | Anonymous       |
| POST   | /api/auth/signup                                      | Register new user                     | Anonymous       |
| GET    | /api/products/search?ageBracket&incomeBracket&student | Get the products conforming with criteria | User            |
| GET    | /api/products                                         | Get all products                      | Product Manager |
| GET    | /api/products/{productName}                           | Get specified product data            | Product Manager |
| POST   | /api/products                                         | Create new product                    | Product Manager |
| PUT    | /api/products/{productName}                           | Update specified product              | Product Manager |
| DELETE | /api/products/{productName}                           | Delete specified product              | Product Manager |

#### Frontend Service
Contains general user input logic and its validation.

## Persistence
This solution doesn't utilize any sort of database, thus 
all data is stored in memory with some default presets of data provided.

## Security
Security is implemented with JWT Authentication. Both backend endpoints and frontend routes are secured.
There is also a full Login and User Registration functionality with form validation. 
 
 There are two predefined user roles: User and Product Manager. Product Manager role is obtainable 
 is only by using the default product_manager account(password="password"), while all newly
 registered users have only a role of User.
 
## How to run
#### Before you start
- Install Docker and Docker Compose.

#### Production mode
Just copy 'docker-compose.yml' and hit 'docker-compose up'.
In this case, the latest images will be pulled from Docker Hub.
After that just launch your browser and go to 'http://localhost:4200'.

#### Development mode
If you'd like to build images yourself (with some changes in the code, for example),
you have to clone repository and build backend service artifact with maven: 'maven package [-DskipTests]'.
Then, run 'docker-compose -f docker-compose.dev.yml up'.

#### Important endpoints
- http://localhost:4200 - Angular deployed with Nginx
- http://localhost:8080 - Backend service
