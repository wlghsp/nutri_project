package kr.co.bteam.streamView;

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
import kr.co.bteam.streamView.domain.StreamReplyDTO;
import kr.co.bteam.streamView.domain.StreamReplyPageDTO;
import kr.co.bteam.streamView.service.IStreamReplyService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/streplies")
@AllArgsConstructor
public class StreamReplyController {
	
	private IStreamReplyService service;

	private static final Logger log = LoggerFactory.getLogger(StreamReplyController.class);

	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody StreamReplyDTO stReplyDto) {
		log.info("replyDto : " + stReplyDto);

		int insertCount = service.register(stReplyDto);

		log.info("Reply Insert Count :" + insertCount);

		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// 삼항 연산자 처리
	}

	@GetMapping(value = "/pages/{mvno}/{page}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<StreamReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("mvno") int mvno) {

		Criteria cri = new Criteria(page, 10);

		log.info("get Reply List bno : " + mvno);
		log.info("cri : " + cri);

		return new ResponseEntity<>(service.getListPage(cri, mvno), HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.PUT,
			RequestMethod.PATCH }, value = "/{mvrno}", consumes = "application/json", produces = {
					MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody StreamReplyDTO stReplyDto, @PathVariable("mvrno") int mvrno) {

		stReplyDto.setMvno(mvrno);

		log.info("mvrno : " + mvrno);
		log.info("modify : " + stReplyDto);

		return service.modify(stReplyDto) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping(value = "/{mvrno}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("mvrno") int mvrno) {

		log.info("rno : " + mvrno);

		return service.remove(mvrno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{mvrno}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<StreamReplyDTO> read(@PathVariable("mvrno") int mvrno) {
		log.info("read mvrno" + mvrno);

		return new ResponseEntity<>(service.read(mvrno), HttpStatus.OK);
	}
	
	

}
