# spring-cloud-stream-example

## Sequence Diagram

image:create_account.png[Sequence Diagram]

## Getting Start

### Start RabbitMQ

```shell script
docker-compose up 
```

### Start account-service and notification-service

### Testing

* Curl Request

```shell script
curl –location –request POST 'http://localhost:8081/accounts' \
–header 'Content-Type: application/json' \
–data-raw '{
"name":"John Din",
"account_number":"0000-0000-4372"

}'
```

* Responses
```json
{
    "name": "John Din",
    "account_number": "0000-0000-4372",
    "created_date": "2020-08-30"
}
```


### account-service log

```java
2020-08-30 15:28:18.376 INFO 19904 — [nio-8081-exec-6] o.s.a.controller.AccountController : Received the request with account name:John Din
2020-08-30 15:28:18.376 INFO 19904 — [nio-8081-exec-6] o.s.account.service.AccountService : Found [0] with account number:0000-0000-4372
2020-08-30 15:28:18.376 INFO 19904 — [nio-8081-exec-6] o.soyphea.account.output.AccountBinder : Send message =&gt; Account{name='John Din', accountNumber='0000-0000-4372', createDate=2020-08-30} to the channel

```



### notification-service
```java
2020-08-30 15:28:18.399 INFO 19834 — [created_group-1] o.s.notification.input.AccountStreaming : Received account :Account{name='John Din', accountNumber='0000-0000-4372', createDate=2020-08-30} created.

```
