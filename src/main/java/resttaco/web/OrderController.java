package resttaco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import resttaco.data.IngredientRepository;
import resttaco.data.OrderRepository;
import resttaco.data.TacoRepository;
import resttaco.data.UserRepository;
import resttaco.domain.Order;
import resttaco.domain.OrderDTO;
import resttaco.domain.Taco;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/order", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderRepository orderRepository;
    private TacoRepository tacoRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, TacoRepository tacoRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
    }
    // receives an OrderDTO
    // finds user by ID and adds to an Order object
    // finds list of tacos by ID and adds to an Order object
    // insert new order object into Taco_Order table
    // return the Order object to user
    @PostMapping (consumes = "application/json")
    public Order postNewOrder(@RequestBody OrderDTO dto) {
        ArrayList<Taco> orderTacos = new ArrayList<Taco>();
        dto.getTacoIds().forEach(
                tacoId-> orderTacos.add(tacoRepository.findById(tacoId).orElse(null))
        );
        // creates a new Order object and adds to taco list and dtoOrder to object
        Order order = new Order(dto.getName(), dto.getStreet(), dto.getCity(), dto.getState(), dto.getZip(), dto.getCcNumber(), dto.getCcExpiration(), dto.getCcCVV(), orderTacos);
        // finds user by id and adds to an order object
        order.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        return orderRepository.save(order);
    }
}
