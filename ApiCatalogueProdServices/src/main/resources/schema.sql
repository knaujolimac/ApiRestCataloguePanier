create table if not exists TypeProduit(
  idType identity,
  nomType varchar(50) not null,
  etatType int not null,
);


create table if not exists Produit(
  idProduit identity,
  nomProduit varchar(50) not null,
  prixProduit decimal(10,2) not null,
  typeProduit bigint not null
);

alter table Produit
    add foreign key (typeProduit) references TypeProduit(idType);
	

create table if not exists PanierAchete(
	idPanierAchete identity,
	idProduit bigint not null,
	cantite int not null,
	dateCreation timestamp not null,
	dateModification timestamp not null
);

alter table PanierAchete
    add foreign key (idProduit) references Produit(idProduit);