package app.WithoutAnnotation;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class main_recv {
    //Задаем имя для очереди:
    private final static String QueueName = "hello";

    public static void main(String[] args) throws Exception {
        //Создаем соединение
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QueueName, false, false, false, null);
        System.out.println("[*] Waiting for messages. To exit press CTRL+C");
        //DeliverCallback - функ. интерфейс (т.е. с одним абстр. нереализованным методом)
        //обратного вызова для уведомления о доставке сообщения.
        //Для реализации предпочтительно использовать лямбда-выражения
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        //Запускаем пользователя
        channel.basicConsume(QueueName, true, deliverCallback, consumerTag -> { });
    }
}
