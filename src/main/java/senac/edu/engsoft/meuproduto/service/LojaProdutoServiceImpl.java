package senac.edu.engsoft.meuproduto.service;

import javafx.util.Pair;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.advice.exception.EntityModelNotFoundException;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.Produto;
import senac.edu.engsoft.meuproduto.model.dto.LojaProdutoDTO;
import senac.edu.engsoft.meuproduto.service.repository.LojaProdutoRepository;

import java.util.Optional;

@Service
public class LojaProdutoServiceImpl implements LojaProdutoService {

	private final LojaProdutoRepository lojaProdutoRepository;
	private final LojaService lojaService;
	private final ProdutoService produtoService;

	public LojaProdutoServiceImpl(LojaProdutoRepository lojaProdutoRepository,
								  LojaService lojaService,
								  ProdutoService produtoService) {
		super();
		this.lojaProdutoRepository = lojaProdutoRepository;
		this.lojaService = lojaService;
		this.produtoService = produtoService;
	}

//	@Override
//	public Iterable<LojaProduto> getAll() {
//		return lojaProdutoRepository.findAll();
//	}

	@Override
	public LojaProduto getByLojaIdAndProdutoId(Long lojaId, Long produtoId) {
		LojaProduto _lojaProduto = lojaProdutoRepository.getByLojaIdAndProdutoId(lojaId, produtoId).orElseThrow(() -> new EntityModelNotFoundException(LojaProduto.class, lojaId, produtoId));
		return _lojaProduto;
	}

	@Override
	public Iterable<LojaProduto> getAll() {
		return lojaProdutoRepository.findAll();
	}

	@Override
	public LojaProduto save(LojaProdutoDTO lojaProdutoDTO) {
		Pair<Loja, Produto> lojaProdutoPair = validateLojaProduto(lojaProdutoDTO);
		return lojaProdutoRepository.save(new LojaProduto(lojaProdutoPair.getKey(), lojaProdutoPair.getValue(), lojaProdutoDTO.getPreco()));
	}

	@Override
	public LojaProduto update(LojaProdutoDTO lojaProdutoDTO) {
		validateLojaProduto(lojaProdutoDTO);
		LojaProduto _lojaProduto = lojaProdutoRepository.getByLojaIdAndProdutoId(lojaProdutoDTO.getIdLoja(), lojaProdutoDTO.getIdProduto()).orElseThrow(() -> new EntityModelNotFoundException(LojaProduto.class, lojaProdutoDTO.getIdLoja(), lojaProdutoDTO.getIdProduto()));
		_lojaProduto.setPreco(lojaProdutoDTO.getPreco());
		return lojaProdutoRepository.save(_lojaProduto);
	}

	private Pair<Loja, Produto> validateLojaProduto(LojaProdutoDTO lojaProdutoDTO){
		Optional<Loja> loja = lojaService.getById(lojaProdutoDTO.getIdLoja());
		Optional<Produto> produto = produtoService.getById(lojaProdutoDTO.getIdProduto());
		if(!loja.isPresent()){
			throw new EntityModelNotFoundException(lojaProdutoDTO.getIdLoja());
		}
		if(!produto.isPresent()){
			throw new EntityModelNotFoundException(lojaProdutoDTO.getIdProduto());
		}
		return new Pair<Loja, Produto>(loja.get(), produto.get());
	}

	@Override
	public void delete(Long id) {
		lojaProdutoRepository.deleteById(id);
	}



}
