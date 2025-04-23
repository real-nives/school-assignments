package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (partRepository.count() == 0) {

            InhousePart driverHead = new InhousePart();
            driverHead.setName("Driver Head");
            driverHead.setPrice(59.99);
            driverHead.setInv(5);
            driverHead.setMinInv(1);
            driverHead.setMaxInv(25);

            InhousePart driverShaft = new InhousePart();
            driverShaft.setName("Driver Shaft");
            driverShaft.setPrice(74.99);
            driverShaft.setInv(5);
            driverShaft.setMinInv(1);
            driverShaft.setMaxInv(25);

            InhousePart clubGrip = new InhousePart();
            clubGrip.setName("Club Grip");
            clubGrip.setPrice(19.99);
            clubGrip.setInv(5);
            clubGrip.setMinInv(1);
            clubGrip.setMaxInv(25);

            InhousePart putterHead = new InhousePart();
            putterHead.setName("Putter Head");
            putterHead.setPrice(49.99);
            putterHead.setInv(5);
            putterHead.setMinInv(1);
            putterHead.setMaxInv(25);

            InhousePart putterShaft = new InhousePart();
            putterShaft.setName("Putter Shaft");
            putterShaft.setPrice(54.99);
            putterShaft.setInv(5);
            putterShaft.setMinInv(1);
            putterShaft.setMaxInv(25);

            partRepository.save(driverHead);
            partRepository.save(driverShaft);
            partRepository.save(clubGrip);
            partRepository.save(putterHead);
            partRepository.save(putterShaft);
        }

        if (productRepository.count() == 0) {

            Product beginnerClubSet = new Product("Beginner Club Set", 199.99, 5);
            Product golfBallSet = new Product("Golf Ball Set", 29.99, 5);
            Product tees =  new Product("Tees", 4.99, 5);
            Product golfShoes = new Product("Golf Shoes", 109.99, 5);
            Product rangefinder = new Product("Rangefinder", 69.99, 5);

            productRepository.save(beginnerClubSet);
            productRepository.save(golfBallSet);
            productRepository.save(tees);
            productRepository.save(golfShoes);
            productRepository.save(rangefinder);

        }
       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
