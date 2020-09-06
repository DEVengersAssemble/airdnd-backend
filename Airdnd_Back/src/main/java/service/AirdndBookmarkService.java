package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AirdndBookmarkDAO;
import vo.AirdndBookmarkVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndHomePictureVO;

@Service("airdndBookmarkService")
public class AirdndBookmarkService implements AirdndBookmarkServiceI {
	@Autowired
	AirdndBookmarkDAO airdnd_bookmark_dao;
	
	//Select bookmark list
	@Override
	public List<AirdndBookmarkVO> selectBookmark(){
		List<AirdndBookmarkVO> list = airdnd_bookmark_dao.selectBookmark();

		return list;
	}
	
	//Select bookmark homes
	@Override
	public List<AirdndBookmarkedHomesVO> selectBookmarkHomes() {
		List<AirdndBookmarkedHomesVO> list = airdnd_bookmark_dao.selectBookmarkHomes();

		return list;
	}
	
	//Select bookmark homes where bookidx
	@Override
	public List<AirdndBookmarkedHomesVO> selectBookmarkHomesIdx(int bookmark_idx) {
		List<AirdndBookmarkedHomesVO> list = airdnd_bookmark_dao.selectBookmarkHomesIdx(bookmark_idx);

		return list;
	}
	
	//Search bookmark homes' count
	@Override
	public int selectBookmarkHomesCount(int bookmark_idx) {
		int size = airdnd_bookmark_dao.selectBookmarkHomesCount(bookmark_idx);
		
		return size;
	}
	
	//Select user's reservation home's main picture
	@Override
	public List<AirdndHomePictureVO> selectHomeMainPicture(int home_idx) {
		List<AirdndHomePictureVO> list = airdnd_bookmark_dao.selectHomeMainPicture(home_idx);

		return list;
	}
	
	//Create a new bookmark
	@Override
	public int insert_bookmark(AirdndBookmarkVO vo) {
		int res = airdnd_bookmark_dao.insert_bookmark(vo);
		
		return res;
	}
	
	//Search an idx
	@Override
	public int selectIdx(String bookmark_list_title) {
		int idx = airdnd_bookmark_dao.selectIdx(bookmark_list_title);
		
		return idx;
	}
	
	//Add the home in the bookmark
	@Override
	public int insert_bookmarkHome(AirdndBookmarkedHomesVO vo) {
		int res = airdnd_bookmark_dao.insert_bookmarkHome(vo);
		
		return res;
	}
	
	//Delete the home in the bookmark
	@Override
	public String delete_bookmarkHome(int bookmark_idx) {
		airdnd_bookmark_dao.delete_bookmarkHome(bookmark_idx);
		
		return null;
	}
	
	//Delete the bookmark
	@Override
	public String delete_bookmark(int idx) {
		airdnd_bookmark_dao.delete_bookmark(idx);
		
		return null;
	}
	
	//Update update_date_time
	@Override
	public int update_updateTime(int idx) {
		int res = airdnd_bookmark_dao.update_updateTime(idx);
		
		return res;
	}
}
