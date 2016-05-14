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
			var isCart = false
			var contents = false
			for (token in tokens) {
				println("${token.getSurface()}\t${token.getAllFeatures()}")
				var features = token.getAllFeaturesArray()
				if (features.size == 9) {
					if (features[0] == "名詞") {
						if (features[7] == "カゴ") {
							isCart = true
						}
						if (features[7] == "ボックス") {
							isCart = true
						}
						if (features[7] == "カート") {
							isCart = true
						}
					}
					if (features[7] == "ナカミ") {
						contents = true
					}
					if (features[7] == "トマト") {
						var quantity = cart.get("トマト")
						if (quantity == null) quantity = 0
						cart.put("トマト", ++quantity)
					}
					if (features[7] == "メロン") {
						var quantity = cart.get("メロン")
						if (quantity == null) quantity = 0
						cart.put("メロン", ++quantity)
					}
					if (features[6] == "終わる" || features[6] == "おわる") {
						end = true
					}
				}
			}
			if (isCart) {
				if (contents) {
					println("定期ボックスには")
					cart.forEach { println(it) }
					println("が入っているよ")
				}
			}
		}
	}
}