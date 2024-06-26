# Lytics
> Library and Sample App for Analytics

### Dev Setup:
* Clone the repository.
* Build App from app module, Explore MainActivity for example usage of lytics (Analytics) library

### Basic flow
* Upon initializing the SDK, SDK automatically collects events like app_open, screen_view
* User has option to send custom events with properties too
* These collected events are stored in sqlite and are synced later using WorkManager
* User has option to opt out of collecting and recording events

### Limitations: 
* Syncing of events to app-server is not performed yet however request payload has been created 