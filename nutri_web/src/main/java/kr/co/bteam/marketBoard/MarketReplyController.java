package kr.co.bteam.marketBoard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bteam.marketBoard.domain.ReplyDTO;
import kr.co.bteam.marketBoard.domain.ReplyPageDTO;
import kr.co.bteam.marketBoard.service.IMarketReplyService;
import kr.co.bteam.noticeBoard.Criteria;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/replies")
@AllArgsConstructor
public class MarketReplyController {
	
	private IMarketReplyService service;

	private static final Logger log = LoggerFactory.getLogger(MarketReplyController.class);

	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyDTO replyDto) {
		log.info("replyDto : " + replyDto);

		int insertCount = service.register(replyDto);

		log.info("Reply Insert Count :" + insertCount);

		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// 삼항 연산자 처리
	}

	@GetMapping(value = "/pages/{m_no}/{page}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("m_no") int m_no) {

		Criteria cri = new Criteria(page, 10);

		log.info("get Reply List bno : " + m_no);
		log.info("cri : " + cri);

		return new ResponseEntity<>(service.getListPage(cri, m_no), HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.PUT,
			RequestMethod.PATCH }, value = "/{rno}", consumes = "application/json", produces = {
					MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDto, @PathVariable("rno") int rno) {

		replyDto.setRno(rno);

		log.info("rno : " + rno);
		log.info("modify : " + replyDto);

		return service.modify(replyDto) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping(value = "/{rno}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") int rno) {

		log.info("rno : " + rno);

		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyDTO> read(@PathVariable("rno") int rno) {
		log.info("read rno" + rno);

		return new ResponseEntity<>(service.read(rno), HttpStatus.OK);
	}
	
	

}
