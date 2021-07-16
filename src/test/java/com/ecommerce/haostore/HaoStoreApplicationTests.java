package com.ecommerce.haostore;

import com.ecommerce.haostore.model.Category;
import com.ecommerce.haostore.model.Product;
import com.ecommerce.haostore.model.Role;
import com.ecommerce.haostore.model.User;
import com.ecommerce.haostore.repository.CategoryRepository;
import com.ecommerce.haostore.repository.ProductRepository;
import com.ecommerce.haostore.repository.RoleRepository;
import com.ecommerce.haostore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class HaoStoreApplicationTests {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    //TestCategory
    @Test
    public void testCategory() {
        //init data <begin>
        Category category = new Category();
        category.setName("newCate");
        //init data <end>

        //getAllCategory()
        List<Category> list = categoryRepository.findAll();
        System.out.println(list.isEmpty());
        //updateCategory() <for create>
        category = categoryRepository.save(category);
        //updateCategory() <for update>
        category.setName("updateNewCate");
        category = categoryRepository.save(category);
        //getCategoryById()
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        category = optionalCategory.get();
        //removeCategoryById()
        categoryRepository.deleteById(category.getId());
    }

    //TestProduct
    @Test
    public void testProduct() {
        //init data <begin>
        Product product = new Product();
        product.setDescription("newDescription");
        product.setImageName("newImageName");
        product.setName("newProduct");
        product.setPrice(200d);
        product.setWeight(0.2d);
        //getCategoryById and set
        product.setCategory(categoryRepository.findById(1).get());
        //init data <end>

        //getAllProduct
        List<Product> list = productRepository.findAll();
        System.out.println(list.isEmpty());
        //updateProduct() <for create>
        product = productRepository.save(product);
        //updateProduct() <for update>
        product.setName("updateProduct");
        product = productRepository.save(product);
        //getProductById()
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        product = optionalProduct.get();
        //removeProductById()
        productRepository.deleteById(product.getId());
        //getAllProductByCategoryId()
        List<Product> listByCategory = productRepository.findAllByCategory_Id(1);

    }

    //Test User
    @Test
    public void testUser() {
        //init data <begin>
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("1"));
        user.setFirstName("newFirstName");
        user.setLastName("newLastName");
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1).get());
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        //init data <end>

        //getAllUser()
        List<User> list = userRepository.findAll();
        System.out.println(list.isEmpty());
        //updateUser() <for create>
        user = userRepository.save(user);
        //updateUser() <for update>
        user.setFirstName("updateFirstName");
        user = userRepository.save(user);
        //getUserById()
        Optional<User> opUser = userRepository.findById(user.getId());
        //removeUserById()
        userRepository.deleteById(user.getId());
    }

    //Test Role
    @Test
    public void testRole() {
        //init data <begin>
        Role role = new Role();
        //id 1-admin, 2-user
        //init data <end>

        //getAllRole()
        List<Role> list = roleRepository.findAll();
        System.out.println(list.isEmpty());
        //findRoleById()
        Optional<Role> optionalRole = roleRepository.findById(1);
        role = optionalRole.get();

    }

    //Test login
    @Test
    public void testLogin() {
        List<User> list = userRepository.findAll();
        list.forEach(item -> {
            if (item.getEmail().equals("admin@gmail.com") && bCryptPasswordEncoder.matches("1", item.getPassword())){
                System.out.println("login success");
            }else {
                System.out.println("login fail");
            }
        });
    }

}
