// package jp.co.axa.apidemo.repositories;

// import static org.hamcrest.MatcherAssert.*;
// import static org.hamcrest.Matchers.*;
// import static org.junit.Assert.assertThat;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.util.List;
// import java.util.Optional;

// import org.dbunit.database.DatabaseConnection;
// import org.dbunit.database.IDatabaseConnection;
// import org.dbunit.database.QueryDataSet;
// import org.dbunit.dataset.IDataSet;
// import org.dbunit.dataset.excel.XlsDataSet;
// import org.dbunit.dataset.xml.FlatXmlDataSet;
// import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
// import org.dbunit.operation.DatabaseOperation;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.test.context.transaction.AfterTransaction;
// import org.springframework.test.context.transaction.BeforeTransaction;
// import org.springframework.transaction.annotation.Transactional;

// import jp.co.axa.apidemo.entities.Employee;

// @SpringBootTest
// @Transactional
// public class EmployeeRepositoryTest {
//     @Autowired
//     JdbcTemplate jdbctemplate;

//     @Autowired
//     EmployeeRepository employeeRepository;

//     String employee_id_str = null;
    
//     Long employee_id_num = null;
    
//     private File file = null;
    
//     private FileOutputStream out;
    
//     private FileInputStream in;
    
//     File tempDir = new File("src/test/java/jp/co/axa/apidemo/repositories");

//     @BeforeTransaction
//     public void initdb() throws Exception {
//         Connection connection = jdbctemplate.getDataSource().getConnection();
//         IDatabaseConnection dbconnection = new DatabaseConnection(connection);
//         try {
//             String sql = "SHOW TABLE STATUS where name = 'EMPLOYEE'";
//             PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet result = statement.executeQuery();
//             while (result.next()) {
//                 employee_id_str = result.getString("Auto_increment");
//             }

//             QueryDataSet partialDataSet = new QueryDataSet(dbconnection);
//             partialDataSet.addTable("EMPLOYEE");
//             file = File.createTempFile("EMPLOYEE", ".xml", tempDir);
//             out = new FileOutputStream(file);
//             FlatXmlDataSet.write(partialDataSet, out);
//             out.flush();
//             out.close();

//             IDataSet dataset = new XlsDataSet(new File("src/test/java/jp/co/axa/apidemo/repositories/EmployeeRepositoryTest.xlsx"));
//             DatabaseOperation.CLEAN_INSERT.execute(dbconnection, dataset); 
//         } catch (Exception e) {
//             e.printStackTrace();
//         } finally {
//             connection.close();
//             dbconnection.close();
//         }
//     }

//     @AfterTransaction
//     public void teardown() throws Exception {
//         Connection connection = jdbctemplate.getDataSource().getConnection();
//         IDatabaseConnection dbconnection = new DatabaseConnection(connection);
//         try {

//             in = new FileInputStream(file);
//             IDataSet dataSet= new FlatXmlDataSetBuilder().build(in);
//             DatabaseOperation.CLEAN_INSERT.execute(dbconnection,dataSet);

//             String sql = new StringBuilder("ALTER TABLE EMPLOYEE AUTO_INCREMENT=").append(employee_id_str).toString();
//             PreparedStatement statement = connection.prepareStatement(sql);
//             statement.executeUpdate();

//         } catch(Exception e) {
//             e.printStackTrace();
//         } finally {
//             connection.close();
//             dbconnection.close();
//             file.deleteOnExit();    // DBバックアップファイルの削除
//         }
//     }

//     @Test
//     public void test_findAll() {
//         List<Employee> employees = employeeRepository.findAll();
//         System.out.println(employees.get(0));
//     }

//     @Test
//     public void test_findById() {
//         Optional<Employee> employee = employeeRepository.findById(1L);
//         assertThat(employee.get().getId(), is(1L));
//     }
// }
