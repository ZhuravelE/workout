package com.zhuravel.dao;

import com.zhuravel.model.Engine;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class EngineDAOTest {

    private SessionFactory factory;

    private DAO<Engine, Integer> engineDAO;

    private final Engine testEngine = new Engine();

    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        engineDAO = new EngineDAO(factory);
        testEngine.setModel("test_" + LocalTime.now());
        testEngine.setPower(1);
    }

    @After
    public void after() {
        if (engineDAO.read(testEngine.getId()) != null) {
            engineDAO.delete(testEngine);
        }
        factory.close();
    }

    @Test
    public void whenCreateEngineThenEngineIsExist() {
        engineDAO.create(testEngine);
        int id = testEngine.getId();

        Engine result = engineDAO.read(id);

        assertThat(testEngine, is(result));
    }

    @Test
    public void whenReadEngineByModelThenSuccess() {
        engineDAO.create(testEngine);

        Engine result = engineDAO.read(testEngine.getModel());

        assertThat(testEngine, is(result));
    }

    @Test
    public void whenEngineUpdateThenPowerHasNewValue() {
        engineDAO.create(testEngine);
        int id = testEngine.getId();

        testEngine.setPower(2);
        engineDAO.update(testEngine);

        Engine result = engineDAO.read(id);

        assertThat(result.getPower(), is(2));
    }

    @Test
    public void whenEngineDeleteThenEngineNotExist() {
        engineDAO.create(testEngine);
        int id = testEngine.getId();

        Engine before = engineDAO.read(id);
        engineDAO.delete(testEngine);

        Engine after = engineDAO.read(id);

        assertNotNull(before);
        assertNull(after);
    }
}
