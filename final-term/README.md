# Final

I decided to make 2 endpoints for pessimistic and optimistic locking.


## Screenshot

### Home page

![alt text](https://raw.githubusercontent.com/raknatee/ait_sad_hws_labs/master/final-term/image/homeO.PNG)

![alt text](https://raw.githubusercontent.com/raknatee/ait_sad_hws_labs/master/final-term/image/homeP.PNG)

## Controller

HomeController.java
```java
@Controller
public class HomeController {
	
	@Autowired
	private ProductDAO pDao;
	
	@Autowired
	private ProductService ProductService;
	
	@RequestMapping(path = "/p",method=RequestMethod.GET)
	public ModelAndView homeP() {
		ModelAndView mv =  new ModelAndView("/home.jsp");
		List<Product> products = pDao.findAll();
		mv.addObject("products",products);
		mv.addObject("locking_type","pessimistic");
		return mv;
	}
	@RequestMapping(path = "/o",method=RequestMethod.GET)
	public ModelAndView homeO() {
		ModelAndView mv =  new ModelAndView("/home.jsp");
		List<Product> products = pDao.findAll();
		mv.addObject("products",products);
		mv.addObject("locking_type","optimistic");
		return mv;
	}
	
	@Data
	class ObjectBuy{
		private int productID;
		private int n_buy;
	}
	
	@RequestMapping(path= "/pessimistic",method=RequestMethod.POST)
	public String buyP(ObjectBuy obj) {
		ProductService.buyPessimistic(obj.productID, obj.n_buy);
		return "redirect:/p";
	}
	@RequestMapping(path= "/optimistic",method=RequestMethod.POST)
	public String buyO(ObjectBuy obj) {
		ProductService.buyOptimistic(obj.productID, obj.n_buy);
		return "redirect:/o";
	}
}

```

home.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>

<h1>Welcome ${locking_type} locking</h1>

<c:forEach items="${products}" var="product">

	<div>
	<h1>Product</h1>
<h2>id: <c:out value="${product.id}"></c:out></h2>
<h2>name: <c:out value="${product.name}"></c:out></h2>	
<h2>price: <c:out value="${product.price}"></c:out></h2>
<h2>stock: <c:out value="${product.stock}"></c:out></h2>

	<form action="/${locking_type}" method="post">
	 <input type="hidden" name="productID" value="${product.id }">
	<label for="n_buy">Buy IT!</label>
	<select name= "n_buy" id="n_buy">
	
	
	
	<c:forEach begin="0" end="${product.stock}" varStatus="loop">
		<option value="${loop.index}">${loop.index}</option>
	</c:forEach>
	
	</select>
	<input type="submit" value="Submit">
	</form>
	</div>

</c:forEach>

</html>
```

## Model

### Product.java
```java

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Company company;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	private BigDecimal price;
	private int stock;

	
	class StockException extends RuntimeException{
		private static final long serialVersionUID = 1L;

		public String toString() {
			return "stock must be >= 0";
		}
	}
	public void decreaseStock(int n) {
		if(this.stock-n >=0) {
			this.stock-=n;
		}else {
			throw new StockException();
		}
		
	}
	public String toString() {
		return this.id + " " + this.stock;
	}
	
}
```

### Company.java
```java
@Entity
@Data
public class Company {
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "company")
	private List<Product> products;
}
```

### Category.java
```java
@Entity
@Data
public class Category {
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;
	
}
```

## Asynchronous Configuration

AsynConfig.java
```java
@Configuration
@EnableAsync
public class AsynConfig {
	
	@Bean(name="taskExecutor")
	public Executor taskExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("ProductThread>");
		executor.initialize();
		return executor;
	}
}

```

## Service

### Product Service

ProductDAO.java
```java

public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	List<Product> findAll(); 
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Product p where id= :id")
	Product findProductForPessimistic(@Param("id") int id);
	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Query("select p from Product p where id= :id")
	Product findProductForOptimistic(@Param("id") int id);

}

```

ProductService.java
```java
@Service
public class ProductService {

	@Autowired
	ProductDAO pDAO;
	
	@Transactional
	@Async
	public void buyPessimistic(int id, int n){
		Product p = null;
		try {
			p = pDAO.findProductForPessimistic(id);
			
		}catch(Exception e) {
			System.err.println("Pessimistic LOCKED");
		}
		
		p.decreaseStock(n);
		pDAO.save(p);
		
	}
	
	@Transactional
	@Async
	public void buyOptimistic(int id, int n){
		Product p = null;
		try {
			p = pDAO.findProductForOptimistic(id);
			
		}catch(Exception e) {
			System.err.println("Optimistic LOCKED");
		}
		
		p.decreaseStock(n);
		pDAO.save(p);
		
	}
	
	
}
```

## Testing

I cannot finish it on time.
```java
@SpringBootTest
class FinalExamApplicationTests {
	
	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {
	}

	@Test
	void testPessimisticLocking() {
		
	}
	
	@Test
	void testOptimisticLocking() {
		
	}
}
```