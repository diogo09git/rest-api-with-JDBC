package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Product;

@Repository
public class JdbcProductRepository implements ProductRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Product> findAll() {
		String sql = "select id, name, type from Product";
		return jdbcTemplate.query(sql, this::mapRowToProduct);
	}

	@Override
	public Product findOne(Integer id) {
		String sql = "select id, name, type from Product where id = ?";
		List<Product> a = jdbcTemplate.query(sql, this::mapRowToProduct, id);
		if(a.isEmpty()) {
			return null;
		}else {
			return a.get(0);
		}
	}

	@Override
	public Product save(Product product) {
		String sql = "insert into Product (id, name, type) values (?,?,?)";
		jdbcTemplate.update(sql,
				product.getId(),
				product.getName(),
				product.getType());
				return product;
	}
	
	@Override
	public void delete(Integer id) {
		String sql = "delete from Product where id=?";
		jdbcTemplate.update(sql, id);
	}
	
	private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
		return new Product(
				rs.getInt("id"), 
				rs.getString("name"), 
				rs.getString("type"));
	}

}
