const fs = require('fs');

let eventos = JSON.parse(fs.readFileSync('eventoscontrole.json'));
let escolhas = JSON.parse(fs.readFileSync('escolhas.json'));
var textFile = "";

function desc2title(text){
	text = text.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "")
	let words = text.split(' '), numWord = 6;
	
	if(words.length>numWord)
		words = words.slice(0, numWord)

	text = words.join(' ')
	if(text.indexOf(',')>-1)
		return text.split(' ').join('_').slice(0, text.indexOf(','))
	if(text.indexOf('.')>-1)
		return text.split(' ').join('_').slice(0, text.indexOf('.'))
	
	return text.split(' ').slice(0,3).join('_')
}

let title, description, choices, firstChoice, endChoice;
for(var evento of eventos){
	title = "=== "+desc2title(evento.descricao)+" ==="
	description = evento.descricao
	
	textFile += title+'\n'
	textFile += description+'\n'
	
	firstChoice = Number(evento.comandoinicial);
	endChoice = Number(evento.comandofinal);
	
	choices = escolhas.slice(firstChoice, endChoice+1)
	for(var choice of choices){
		textFile += '*   '+choice.resposta+' -> '+desc2title(eventos[Number(choice['idsaida'])].descricao)+"\n"
	}
}

console.log(textFile)