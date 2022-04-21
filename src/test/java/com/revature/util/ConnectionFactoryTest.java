package com.revature.util;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConnectionFactoryTest {

    private static ConnectionFactory connectionFactory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        connectionFactory = (ConnectionFactory) ConnectionFactory.connect();
    }

    @Test
    public void testConnectionFactoryIsAbleToGetConnection() throws IOException, SQLException, ClassNotFoundException {
        Connection conn = connectionFactory.getConnection();

        assertThat(conn, instanceOf(Connection.class));
    }
}
