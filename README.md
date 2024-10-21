# OO Design Principle

**1.** **单一职责原则**

(1) 原则：一个类应该只有一个引起它变化的原因。

(2) 体现：

① HttpClient类：专门处理 HTTP 请求的发送，单一职责是发送请求，与支付、退款等业务逻辑无关。

② ResponseParser类：专门用于解析 JSON 响应数据，单一职责是处理数据解析。

③ Client 类：主要负责与支付接口交互，专注于业务逻辑，不承担过多的职责。

**2.** **依赖倒置原则**

(1) 原则：高层模块不应该依赖于低层模块，而应该依赖于抽象。

(2) 体现：Client 类依赖 HttpClient 接口和 ResponseParser 接口，而不直接依赖具体的实现，并且使用依赖注入的方式降低了模块之间的耦合，使系统更易于扩展和维护。

**3.** **组合复用原则**

(1) 原则：尽量使用组合而不是继承来扩展。

(2) 体现：Client 类通过组合的方式使用了 HttpClient 和 ResponseParser，而不是继承这些类，这样既可以复用 HttpClient 和 ResponseParser 的功能，同时也能保持灵活性。通过依赖注入，Client 可以根据不同的需求注入不同的实现类，而不必通过继承来实现。

**4.** **接口隔离原则**

(1) 原则：不应强迫客户端依赖它不需要的接口，而是根据不同的职责将接口细分。

(2) 体现：

① HttpClient 接口和 ResponseParser 接口是针对不同职责的接口，HttpClient 只处理 HTTP 请求的发送，而 ResponseParser 专注于数据解析，两者职责清晰，互不干扰。

② Client 类只依赖它实际需要的接口（HttpClient 和 ResponseParser），而不是一个大型的包含所有功能的接口，这样避免了不必要的依赖，使得 Client 类可以保持简洁。

**5.** **迪米特原则**

(1) 原则：一个类（对象、模块、系统）应该对类（对象、模块、系统）的内部细节保持最少了解。

体现：Client 类只与它的直接依赖对象（HttpClient 和 ResponseParser）进行交互，而没有深入了解具体的实现细节。例如对Client的全参构造中，Client 调用 ResponseParser 进行数据解析，调用 HttpClient 进行发送请求，只依赖解析器的接口，而不深入了解具体的实现逻辑。
