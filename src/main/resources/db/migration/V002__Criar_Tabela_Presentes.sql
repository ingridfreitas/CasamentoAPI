CREATE TABLE presentes(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL;
    nome_presente VARCHAR(255) NOT NULL;
    descricao TEXT NOT NULL;
    valor FLOAT NOT NULL;
    parcelas VARCHAR(255) NOT NULL;
    link VARCHAR(255) NOT NULL;
    imagem VARCHAR(255) NOT NULL;
);