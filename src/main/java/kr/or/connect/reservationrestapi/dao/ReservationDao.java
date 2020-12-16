package kr.or.connect.reservationrestapi.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationrestapi.dto.*;

import static kr.or.connect.reservationrestapi.dao.ReservationDaoSqls.*;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Category> rowMapper_category = BeanPropertyRowMapper.newInstance(Category.class);
    private RowMapper<Promotion> rowMapper_promotion = BeanPropertyRowMapper.newInstance(Promotion.class);
    private RowMapper<FileInfo> rowMapper_promotionImage = BeanPropertyRowMapper.newInstance(FileInfo.class);
    private RowMapper<WholeServiceInfo> rowMapper_wholeServiceInfo = BeanPropertyRowMapper.newInstance(WholeServiceInfo.class);
    private RowMapper<CommentLists> rowMapper_comment = BeanPropertyRowMapper.newInstance(CommentLists.class);
    private RowMapper<FileInfo> rowMapper_productImage = BeanPropertyRowMapper.newInstance(FileInfo.class);
    private RowMapper<FileInfo> rowMapper_mapImage = BeanPropertyRowMapper.newInstance(FileInfo.class);
    private RowMapper<DisplayInfo> rowMapper_displayInfo = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
    private RowMapper<ReservationInfo> rowMapper_reservationInfo = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
    
    public ReservationDao(DataSource dataSource) { //db연결을 위해 datasource 접근
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_info")
                .usingGeneratedKeyColumns("id");
    }
    
    public List<Category> selectAllCategory(){
		return jdbc.query(SELECT_ALL_CATEGORY,Collections.emptyMap(), rowMapper_category);
	}
    
    public List<Promotion> selectAllPromotion(){
    	return jdbc.query(SELECT_ALL_PROMOTION, Collections.emptyMap(), rowMapper_promotion);
    }
    public List<FileInfo> selectAllPromotionFileName(){
    	return jdbc.query(SELECT_PROMOTION_IMAGE, Collections.emptyMap(), rowMapper_promotionImage);
    }
    
    public List<WholeServiceInfo> selectAllWholeServiceInfo(Integer start, Integer limit){
    	Map<String, Integer> params = new HashMap<>(); // 쿼리문에 바인딩을 위해 map을 씀
		params.put("start", start);
		params.put("limit", limit);
    	return jdbc.query(SELECT_ALL_ITEMS,params,rowMapper_wholeServiceInfo);
    }
    public List<WholeServiceInfo> selectItemDetail(Integer id){
    	Map<String,Integer> params = new HashMap<>();
    	params.put("id", id);
    	return jdbc.query(SELECT_ITEM_DETAIL,params,rowMapper_wholeServiceInfo);
    }
    
    public List<WholeServiceInfo> selectItemsCategory(Integer categoryId, Integer start, Integer limit){
    	Map<String,Integer> params = new HashMap<>();
    	params.put("categoryId", categoryId);
    	params.put("start", start);
    	params.put("limit", limit);
    	return jdbc.query(SELECT_ITEMS_CATEGORY, params,rowMapper_wholeServiceInfo);
    }
    
    public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
    public int selectCountCategory(Integer categoryId) {
    	Map<String,Integer> params = new HashMap<>();
    	params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_CATEGORY, params, Integer.class);
	}
    public List<FileInfo> selectAllProductImage(){
    	return jdbc.query(SELECT_ALL_PRODUCTIMG, Collections.emptyMap(), rowMapper_productImage);
    }
    
    public List<CommentLists> selectComment(Integer productId, Integer commentLimit){
    	Map<String,Integer> params = new HashMap<>();
    	params.put("productId",productId);
    	params.put("commentlimit", commentLimit);
		return jdbc.query(SELECT_COMMENT,params, rowMapper_comment);
	}
    public List<CommentLists> selectAllComment(Integer productId){
    	Map<String,Integer> params = new HashMap<>();
    	params.put("productId",productId);
    	return jdbc.query(SELECT_ALL_COMMENT, params,rowMapper_comment);
    }
    public double avgRate(Integer productId) {
    	Map<String,Integer> params = new HashMap<>();
    	params.put("productId",productId);
		return jdbc.queryForObject(AVG_RATE, params, Double.class);
	}
    public int countComment(Integer productId) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("productId",productId);
		return jdbc.queryForObject(COUNT_COMMENT, params, Integer.class);
	}
    public List<DisplayInfo> selectLocation(Integer productId){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("productId",productId);
    	return jdbc.query(SELECT_LOCATION,params, rowMapper_displayInfo);
    }
    
    public List<DisplayInfo> getId(Integer id) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id",id);
    	return jdbc.query(GET_ID,params, rowMapper_displayInfo);
    }
    public List<FileInfo> getMapImg(Integer id){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id",id);
    	return jdbc.query(MAP_IMG,params, rowMapper_mapImage);
    }
    public List<DisplayInfo> getPlaceAndOpeninghours(Integer id){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("id",id);
    	return jdbc.query(PLACE_OPENINGHOURS,params, rowMapper_displayInfo);
    }
    
    public List<ReservationInfo> getMyReservation(String email){
    	Map<String, String> params = new HashMap<>();
    	params.put("email", email);
    	return jdbc.query(MY_RESERVATION,params, rowMapper_reservationInfo);
    }

    public Long insert(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).longValue();
	}
  
    public int cancelItem(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);//:parameter 정보가 담긴 해당 객체를 인자로 넣어줌
		return jdbc.update(CANCEL_ITEM, params);//update 쿼리 실행
	}
}
