
## Todo List
 
- ###  Main Program
  - [x] security
    - [x] logout
    - [x] Make new User
    - [x] authentication
    - [x] authorisation
        - [x] migrate enpoint to \user\* , \mod\*
  - [x] Messages for login
  - [x] Fix Listener Structure
  - [x] Fix Component Scan (scan one configuration file in EExampleApplication)
  - [x] Fix commandLineRunner
  - [ ] add Warning - as pre-ban
    - [x] Secured User
    - [ ] other secured stuff
    - [ ] service can warn
    - [ ] mod see who is warned
    - [ ] user see he is warned
    - [ ] mod can warn
  - [ ] add public messages
    - [x] modify Message
    - [x] queue for few public messages
    - [x] add to Data Runner
    - [x] make place for display
    - [ ] make posting them
    - [x] modify personal stream to show "public" tick
  - [ ] mod post "ad" to all users
    - [ ] make "ad info" default in listener
    - [x] "ad" object
    - [ ] implement listener in User
    - [ ] add button in mod template
    - [ ] add loggic in Controller
  - [ ] ShutDown as Event
    - [x] event
    - [ ] listenr
    - [ ] button in mod
    
    
- ### Tests
  - [ ] Fix Listener (module) tests Structure
  - [ ] Fix integration tests
  - [ ] Fix ModTests
- ### Docs
  - [ ] Project Description (Mermaid)
  - [ ] Explain Weak List
  - [ ] Explain Security
- ### Future ideas
  - [ ] "publicity" turn on/off for given message on user panel
  - [ ] add Friends
  - [ ] add tags
  - [ ] perhaps sql
  - [ ] validation somewhere

```mermaid
flowchart LR

A[Hard] -->|Text| B(Round)
B --> C{Decision}
C -->|One| D[Result 1]
C -->|Two| E[Result 2]
```

