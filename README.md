# multiService
this is a spring cloud project to excercise

#project and module desc
1.register-center module 
is eureka center,provide service register and finding ability

2.order-client module 
provide order service（also be called micro service,need to be registered to the eureka center）

3.order-client1 module 
is a replication of order-client（all the replications of the order-client service consit of cluster service）

4.consumer module 
is used to invoke order cluster service by registered service name which have been registered to the eureka center
（additional,we can use ribbon to achieve service invoking loadbalance）
