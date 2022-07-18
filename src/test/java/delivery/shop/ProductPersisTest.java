package delivery.shop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import delivery.shop.config.JpaQueryFactoryConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static delivery.shop.product.QProduct.product;

@Transactional
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaQueryFactoryConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductPersisTest {
    @Autowired private JPAQueryFactory queryFactory;
    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void setUp() {
        clear();
    }

    @Test
    void test() throws Exception{




    }


    private void clear() {
        em.clear();
        em.flush();
    }
}
