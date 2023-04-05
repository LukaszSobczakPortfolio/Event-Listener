
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
  - [x] add Warning - as pre-ban
    - [x] Secured User
    - [x] other security stuff
    - [x] listener
    - [x] service sends warning
    - [x] mod see who is warned
    - [x] user see he is warned
    - [x] mod can warn
  - [x] add public messages
    - [x] modify Message
    - [x] queue for few public messages
    - [x] add to Data Runner
    - [x] make place for display
    - [x] make posting them
    - [x] modify personal stream to show "public" tick
  - [x] make "ad info" default in listener
    - [x] remove infos from unnecesary places
  - [x] mod post "ad" to all users    
    - [x] "ad" object
    - [x] implement listener in User
    - [x] implement AdService
    - [x] add ads in user template
    - [x] add form in mod template
    - [x] add logic in Controller
  - [x] ShutDown as Event
    - [x] event
    - [x] listenr
    - [x] button in mod
 
  - [x] redirect to user panel or mod panel depending on role  
    
- ### Tests
  - [ ] Fix Listener (module) tests Structure
  - [ ] Fix integration tests
    - [x] acces
        - [x] unauthorized
        - [x] user
        - [x] mod
    - [ ] login
        - [ ] user
        - [ ] admin
        - [ ] failed
        - [ ] failed - suspended
    - [ ] user
        - [ ] is warned
        - [ ] is not warned
        - [ ] post public message
            - [ ] seen by other
        - [ ] post private message
            - [ ] not seen by other
        - [ ] logout
        - [ ] admin not visible
        - [ ] admin not reachable
    - [ ] admin
        - [ ] warn user
        - [ ] ban user
        - [ ] post ad
        - [ ] change to user
        - [ ] change beck to admin
        - [ ] logout
    - [ ] adding user
        - [ ] user
        - [ ] mod
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
  - [ ] top level /mod or /user in controler
```mermaid
flowchart LR

A[Hard] -->|Text| B(Round)
B --> C{Decision}
C -->|One| D[Result 1]
C -->|Two| E[Result 2]
```

