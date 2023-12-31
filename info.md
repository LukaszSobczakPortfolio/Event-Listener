Note: Weak Event Storage has HashMap.

Heart (aka module):

- LccPostProcessor implements (ILccPostProcessor extends BeanPostProcessor);

  - funkcja: ładuje się na początku, Skanuje każdy pojawiający się bean. Jeśli znajdzie (instanceof LccEventListener) to dodaje do kolekcji "IEventStorage".
Listener musi mieć annotację @LccListenerClass 
//TODO weryfikacja extends + annotacja

- Event Dispatcher implements Dispatcher Interface

  - Funkcja: Jest Autowired w beanach "klienckich", można do niego wysłać Ewent ( = POJO implements LccEvent). Ewent jet przekazywana do konkretnych listenerów.

Jest też Example jak to zastosować:

- Login w Spring Security:
  - Dwie role, USER i ADMIN.
  - logika "po loginie" w security.LoginRedirectHandler

- Front jest robiony w Thymeleafie.
  - Trzy kontrolery, jeden do loginu, drugi dla userów, trzeci dla Modów

- User pisze wpisy. Może je oznaczać jako prywatne albo publiczne. Widzi publiczne i swoje prywatne. wpisy idą do "automatycznej moderacji". Jeśli we wpisie jest słowo "bomb" wpis idzie do ręcznej moderacji. mod może dać userowi warning albo bana, albo nic.
mod moze robic wpisy, jest userem.  

  - Happy Path: user controller -> InMemoryMessageService -> (if bomb -> dispatch ModBombEvent)

  - ModBombEvent -> ModService.addMessageForModeration

  - ModController -> dostaje kilka MessagesForModeration -> dispatch BanEvent, WarnEvent albo uznaje ze wiadomosc ok.

  - Ban/Warn event -> ListenerForModifyUsers -> daje bana albo warning dla usera

  - Bonus Zamkniecie jvm: ModController -> ModService -> dispatch ShutDownEvent -> System Service zamyka jvm

  - Bonus wiadomosc do wszystkich: ModController -> AdEvent -> AdService -> każdy User widzi Adsy 

- Braki: brak usuwania i porawiania wpisów, brak SQL, w wielu miejscach tylko happy patch.
Nadmiar: Chaos i Bałagan

