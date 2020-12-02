package app.WithAnnotation;

import jdk.jfr.Enabled;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//Получатель
@EnableRabbit
@Component
public class listener {
    //В этот метод будут приходить сообщения из нашей очереди
    //Данная аннотация определяет, какую очередь будем слушать, а также создает слушателя.
    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        //просто выводим инфу о том, что сообщение пришло + само сообщение
        System.out.println("Received from queue 1: " + message);
    }
}
