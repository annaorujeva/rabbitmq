package app.WithAnnotation;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Контроллер отправляет сообщения
@Controller
public class controller {

    @Autowired
    RabbitTemplate connection;

    @GetMapping("/emit")
    public ResponseEntity<String> queue1() {
        //отправляем сообщение в очередь queue1
        connection.convertAndSend("queue1","Message to queue");
        //отображаем в браузере, что операция выполнена
        return new ResponseEntity<>("emit to queue1", HttpStatus.OK);
    }
}
