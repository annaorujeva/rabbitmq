package app.WithAnnotation;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class config {

    //настраиваем соединение с RabbitMQ через класс CachingConnectionFactory, который обеспечивает кэширование сессии
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connection = new CachingConnectionFactory("localhost");
        return connection;
    }

    //Вспомогательный класс, упрощающий синхронный доступ к RabbitMQ (отправка и получение сообщений),
    //единственное обязательное свойство - это ConnectionFactory.
    //
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    //объявляем очередь с именем queue1
    @Bean
    public Queue myQueue1() {
        return new Queue("queue1");
    }
}
