package br.com.hugo.services;

import br.com.hugo.converter.DozerConverter;
import br.com.hugo.data.model.Book;
import br.com.hugo.data.vo.v1.BookVO;
import br.com.hugo.exception.ResourceNotFoundException;
import br.com.hugo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		return vo;
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(bookRepository.findAll(), BookVO.class);
	}
	
	public BookVO findById(Long id) {
		var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public BookVO update(BookVO book) {
		var entity = bookRepository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		Book entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		
		bookRepository.delete(entity);
	}
	
}
