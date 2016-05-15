package com.oisix.sample.conversation

import java.util.Scanner
import com.atilika.kuromoji.ipadic.Tokenizer

class Conversation {
	fun execute() {
		var end = false
		var cart : MutableMap<String, Int> = mutableMapOf()
		while (!end) {
			var scan = Scanner(System.`in`)
			var input = scan.nextLine()
			println(input)
			var tokenizer = Tokenizer()
			var tokens = tokenizer.tokenize(input)
			var isproducts = false
			var contents = false
			var cartadd = false
			var products : MutableList<String> = mutableListOf()
			for (token in tokens) {
				println("${token.getSurface()}\t${token.getAllFeatures()}")
				var features = token.getAllFeaturesArray()
				if (features.size == 9) {
					if (features[0] == "名詞") {
						if (features[7] == "カゴ") {
							isproducts = true
						}
						if (features[7] == "ボックス") {
							isproducts = true
						}
						if (features[7] == "カート") {
							isproducts = true
						}
						if (features[7] == "ナカミ") {
							contents = true
						}
						if (features[7] == "トマト") {
							products.add("トマト")
						}
						if (features[7] == "メロン") {
							products.add("メロン")
						}
					}
					if (features[6] == "終わる" || features[6] == "おわる") {
						end = true
					}
					if (features[6] == "くださる") {
						cartadd = true
					}
				}
			}
			if (cartadd) {
				if (!products.isEmpty()) {
					products.forEach { 
						var quantity = cart.get(it)
						if (quantity == null) quantity = 0
						cart.put(it, ++quantity)
						println("${it}をカートに入れました")
					}
				} else {
					println("何がほしいですか？")
				}
			}
			if (isproducts) {
				if (contents) {
					println("定期ボックスには")
					cart.forEach { println(it) }
					println("が入っているよ")
				}
			}
		}
	}
}