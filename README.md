[![Java CI with Maven - auto created](https://github.com/LukaszSobczakPortfolio/Event-Listener/actions/workflows/maven.yml/badge.svg)](https://github.com/LukaszSobczakPortfolio/Event-Listener/actions/workflows/maven.yml)

# Event-Listener
Playground for Java and Spring Boot. 
## Features:
- Weak references
- BeanPostProcessor
- Generics
- The usual stuff: Thymeleaf, Dependency Injection, Tests 
 
## Known issues
- No security. Yet.
- Tests test mostly happy path, a few use @DirtiesContext.
- No validation, no error handling
## Description:
### Listener part:
Bean *LccPostProcessor* (BeanPostProcessor) scans all beans, looking for instances of *LccEventListener*, and keeps them stored in a map *(Weak EventMap)*, which uses weak references because a listener can have Session Scope. Using regular references would lead to a memory leak.

Every bean can @Autowire the *DispatcherInterface bean*, which has one method: 
	void dispatch(LccEvent event).   
Dispatcher looks into storage and passes the event to all proper listeners
### Example Part:
I wrote simple application to demonstrate that LccListener works. After login (PASSWORDS NOT HASHED etc.) user can write messages, notes to self. If he used word BOMB, *MessageService* would emit event to *VerificationService*. Then *Mod* can either dismiss message as false positive, or emit *BanEvent* to *UserService*. Banned user cannot write more messages. Program is stateful, *User* and *Mod* are Session scoped
