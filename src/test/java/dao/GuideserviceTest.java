package dao;

import com.example.guid.model.AtributesEntities;
import com.example.guid.model.Entities;


import com.example.guid.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = PersistenceContextIntegrationTest.class)
@Transactional(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@RequiredArgsConstructor
public class GuideserviceTest {

    private GuideService guideService;

    @Test
    public void addGuide() {
        List<AtributesEntities> list = new ArrayList<>();
        list.add(new AtributesEntities().setIdEntities(1l).setName("DEPARTMENT").setIdType(1l));
        guideService.addGuide(new Entities().setName("Department").setDescription("Company"), list);
    }
    @Test
    public void updateGuid() {
        guideService.editGuid("Department");
    }

    @Test
    public void deleteGuid() {
        guideService.deleteGuid("Department");
    }

}
