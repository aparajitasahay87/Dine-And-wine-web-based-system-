# Dine-And-wine-web-based-system-
built a Dine with wine three tier web architecture system using EJB servlets
Assignment 4: Dine with Wine 
Aparajita Sahay
Date: 12/17/2015
I.	Documentation:
a.	“Dine with Wine” is a wine catalog application where a logged in user can search for wine and can add wine to shopping cart and can check out shopping cart.
b.	Feature implemented is : 
i.	Authentication of user: User have to login to the application by providing username and password. Application validates the credentials in database and return the result back to the user. Invalid user have to login again and valid user can access wine details.
ii.	Search of wine: User can search for wine such as red or white wine by inputting data into text box. Application will fetch wine of a specific category from the wine catalog stored in the database.
iii.	Add to shopping cart: user has option to add wines to the shopping cart. By clicking checkboxes user can add multiple wines to cart. Shopping cart is a statefull session and retains the state of shopping cart through each session.
c.	Application has a three tier architecture with model view controller framework.
i.	First tier is a client i.e. browser that gives HTTP request to the server.
ii.	Second tier is a Servlet that handles http request. Second tier is decoupled into two layers a. servlet application layer and EJB the business logic layer. 
Servlet accepts http request and redirect the request to EJB that performs computation on the request and gives the result back to servlet. EJB also interact with the third tier database or web services to access persistent data.
iii.	 Third tier is a database. Application stores wine details, user details and shopping cart details in the database. As per client’s request some create, read, update or delete operation is performed on database and the result is displayed back to client through EJB.
iv.	Server has a MVC framework with JSP as view , servlet as controller and EJB as model 
v.	Servlet is multithreaded and handles multiple client request at a time.
d.	EJB : Application has following beans:
i.	2 entity beans.
ii.	3 session beans - UserInfoFascade session bean - @stateless, ShoppingCart Session Bean -  @statefull bean, WineFascade Session Bean – @stateless bean. 
iii.	User session bean and Wine Session bean are stateless session bean because using these two beans EJB only interacts with the database and no intermediate results for each session has to persist. User session bean authenticate the user by checking the database and Wine session bean fetch the wine details from the database. 
iv.	EJB used entity manager API to perform “CRUD” operation on database.
v.	ShoppingCart Session Bean is a @statefull bean. Shopping cart EJB maintains the state of shopping cart for each session. Once the session is completed shopping cart details is no longer persisted in the application. 
vi.	To persist the current session’s shopping cart details into the database, checkout button is used. It updates the shopping cart details into the database using entity manager.
vii.	Servlet: 
Login Servlet uses @stateless usersession bean for business logic.
Search servlet uses @stateless session bean.
Shopping servlet uses @statefull session bean.
Stateless EJB: To access stateless EJB from servlet, @EJB annotation is injected in servlet to get EJB instance. However, in shopping servlet to access @statefull EJB session bean JNDI lookup is used to make the statefull session bean thread safe. 
viii.	@SessionBeanLocal: Used local session bean.
1.	High Performance in comparison to remote interface because with remote interface network latency can be an overhead.
2.	Scalability is tied to Web front end server and cannot be scaled independently.

II.	 Discussion :
a.	Performance : 
                 To interact with client, my application’s session uses @Local Interface. Since, EJB and servlet is under one JVM machine therefore unlike @remote interface there will be no network latency which could be one of the factor that can affect the performance of the application. 
Also by using remote interface, server uses pass by value sematic to call remote interface of a bean, this can be more expensive since clients using pass-by-value semantics must copy arguments before passing them to the EJB component. However, local interface will be less expensive where client can use pass be reference semantics to access local interface.

b.	Reliability:  Currently servlet and EJB is in one machine. If there are multiple requests to a particular session then data request traffic can increase the load in one EJB machine and hence can affect the response time for the request, which in turn can affect the reliability. To overcome this issue EJB can replicated to multiple remote machines and servlet can use @remote interface to access EJB components. However, chances of data loss due to network issue can affect the reliability of the application.

c.	Usability: Using dependency injection in EJB where container is responsible for managing and resolving relationships between a components its dependencies, it is easy for EJB programmer just to inject valid @annotation to the program and container resolves corresponding dependency. EJB developer do not have resolve dependency which makes EJB programming more usable. EJB provides simple and consistent programming model.



d.	Limitation: 
i.	Currently, all session beans in the application exposes @local interface to clients.  In the application each EJB handles each type of business logic. However, if the clients request is high for a particular feature in the application and the business logic to cater that request is present in only one EJB session then performance can be affected. However, by replicating EJB business logic among multiple remote machines and by exposing @remote interface for client to access object of the session performance can be improved.
ii.	If two clients want to purchase 1 single item in a wine catalog at the same time 
Application will allow the client to do so which in turn will create inconsistency within the application. However, by using transaction management we can make application more consistent and reliable.
iii.	Currently, Scalability is tied to Web front end server and cannot be scaled independently. If the programmer makes even one single change to a server or EJB then everything has to be deployed again. However, by segregating the servlet and EJB into different machine scalability factor can be improved.

e.	Possible functional improvement : 
i.	Adding algorithm that recommends wine to user by analyzing the past search details. Currently, EJB fetch data directly from the database and no analysis of data is done to suggest better wines as the user login.
ii.	Currently application will give wrong result in a scenario where two client made a concurrent purchase to a single item in catalog. If one item is purchased at the same time by two different client then that single item will be sold twice. To avoid situation like these I will add EJB transaction management technique in my implementation.
iii.	Currently, JSP file at server side creates a HTML response and gives the result back to the client. Data can be too large and to transfer large data from server to client will require large bandwidth and time. So to improve the performance of the application I will create a json response at server side and gives the result back to client, so that client will handle the data rendering.
