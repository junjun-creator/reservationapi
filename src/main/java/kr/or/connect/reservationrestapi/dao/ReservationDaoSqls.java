package kr.or.connect.reservationrestapi.dao;

public class ReservationDaoSqls {
	public static final String SELECT_ALL_CATEGORY = "SELECT id,name FROM category order by id";
	public static final String SELECT_ALL_PROMOTION = "SELECT product_id FROM promotion order by product_id";
	public static final String SELECT_PROMOTION_IMAGE = "SELECT file_name FROM file_info WHERE id IN (SELECT file_id FROM product_image WHERE type = 'th' AND product_id IN (SELECT product_id FROM promotion))";
	//전체리스트 정보 및 사진이름 쿼리
	public static final String SELECT_ALL_ITEMS = "SELECT product.id, product.description, product.content, display_info.id AS whole_id, display_info.place_name FROM product JOIN display_info ON product.id=display_info.product_id limit :start, :limit";
	public static final String SELECT_ITEM_DETAIL = "SELECT product.description, product.content FROM product JOIN display_info ON product.id=display_info.product_id WHERE display_info.id=:id";
	public static final String SELECT_ALL_PRODUCTIMG = "SELECT file_name FROM file_info WHERE id IN (SELECT file_id FROM product_image)";

	//카테고리 별 정보 및 사진이름 쿼리
	public static final String SELECT_ITEMS_CATEGORY = "SELECT product.id, product.description, product.content, display_info.id AS whole_id, display_info.place_name FROM product JOIN display_info ON product.id=display_info.product_id WHERE product.category_id=:categoryId limit :start, :limit";
	public static final String SELECT_PRODUCTS_CATEGORY = "SELECT category_id, description, content FROM product WHERE category_id = :category_id ORDER BY id";
	public static final String SELECT_PLACENAMES_CATEGORY = "SELECT product_id,place_name FROM display_info WHERE product_id in (SELECT id FROM product WHERE category_id = :category_id) ORDER BY product_id";
	public static final String SELECT_PRODUCTIMGS_CATEGORY = "SELECT file_name FROM file_info WHERE id IN (SELECT file_id FROM product_image WHERE type = 'th' AND product_id IN (SELECT id FROM product WHERE category_id = :category_id))";
	public static final String SELECT_COUNT_CATEGORY = "SELECT count(*) FROM display_info WHERE product_id IN (select id FROM product WHERE category_id=:categoryId)";
	//category별 count 쿼리 작성 완료. ajax로 각 탭 클릭할때 호출하도록 하면 됨.
	
	public static final String SELECT_COUNT = "SELECT count(*) FROM display_info";
	
	public static final String SELECT_COMMENT = "SELECT reservation_info.product_id, product.description, reservation_info.reservation_name, reservation_user_comment.score, reservation_user_comment.comment, reservation_user_comment.create_date FROM reservation_info JOIN reservation_user_comment ON reservation_info.id=reservation_user_comment.product_id JOIN product ON reservation_info.product_id = product.id WHERE product.id = :productId limit 0, :commentlimit";
	public static final String SELECT_ALL_COMMENT = "SELECT reservation_info.product_id, product.description, reservation_info.reservation_name, reservation_user_comment.score, reservation_user_comment.comment, reservation_user_comment.create_date FROM reservation_info JOIN reservation_user_comment ON reservation_info.id=reservation_user_comment.product_id JOIN product ON reservation_info.product_id = product.id WHERE product.id = :productId";
	public static final String AVG_RATE ="SELECT ROUND(AVG(score),1) FROM reservation_user_comment WHERE product_id = :productId";
	public static final String COUNT_COMMENT ="SELECT count(*) FROM reservation_user_comment WHERE product_id = :productId";
	public static final String SELECT_LOCATION = "SELECT id, product_id, place_name, place_lot, place_street, tel FROM display_info WHERE id = :productId";
	public static final String GET_ID = "SELECT product_id FROM display_info WHERE id = :id";
	public static final String MAP_IMG = "SELECT file_name FROM file_info WHERE id = :id";
	public static final String PLACE_OPENINGHOURS = "SELECT place_name, opening_hours FROM display_info WHERE id = :id";
	
	public static final String MY_RESERVATION = "SELECT * FROM reservation_info WHERE reservation_email= :email";
	public static final String CANCEL_ITEM = "UPDATE reservation_info SET cancel_flag = 1 WHERE id = :id";
}
