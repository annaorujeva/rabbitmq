package app.WithoutAnnotation;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class main_send {
    //Задаем имя для нашей очереди:
    private final static String QueueName = "hello";
    public static void main(String[] args) throws Exception{
        //Создаем подключение к серверу RabbitMQ на абстракциях, чтобы самим не устанавливать нужные конфигурации
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            //Объявляем нашу очередь:
            //Аргументы метода queueDeclare(
            // queue: QueueName,
            //    durable: false,
            //    exclusive: false,
            //    autoDelete: false,
            //    arguments: null);
            //queue — название очереди, которую мы хотим создать. Название должно быть уникальным и не может совпадать с системным именем очереди
            //durable — если true, то очередь будет сохранять свое состояние и восстанавливается после перезапуска сервера/брокера
            //exclusive — если true, то очередь будет разрешать подключаться только одному потребителю
            //autoDelete — если true, то очередь обретает способность автоматически удалять себя
            //arguments — необязательные аргументы, которые позволяют дополнительно настроить очередь
            channel.queueDeclare(QueueName, false, false, false, null);
            //Создаем сообщение, которое хотим отправить:
            String message = "Hello World!";
            //Отправляем наше сообщение в точку обмена:
            //basicPublish(
            //extangeName: "" - имя точки обмена
            //queue: QueueName - имя очереди
            //basicProperties: null - дополнительные параметры
            //message: message.getBytes() - т.к. RabbitMQ пересылает в виде массива байтов, то необходимо преобразовать сообщение-строку в такой массив
            channel.basicPublish("", QueueName, null, message.getBytes());
            //Выводим сообщение о том, что сообщение отправлено:
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
