<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<table cellpadding="2" cellspacing="2" border="1">
			<tr>
				<th>Matricule</th>
				<th>Noms</th>
				<th>Prénoms</th>
				<th>Date de naissance</th>
				<th>Lieu de naissance</th>
				<th>Adresse</th>
				<th>Téléphone</th>
				<th>Niveau</th>
				<th>Police d'assurance</th>
			</tr>
			<xsl:apply-templates select="students" />
		</table>
	</xsl:template>
	
	<xsl:template match="students">
		<xsl:apply-templates select="student"></xsl:apply-templates>
	</xsl:template>
	
	<xsl:template match="student">
		<tr>
			<td>
				<xsl:value-of select="matricule"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="noms"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="prenoms"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="dateNaiss"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="lieuNaiss"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="adresse"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="telephone"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="niveau"></xsl:value-of>
			</td>
			<td>
				<xsl:value-of select="police"></xsl:value-of>
			</td>		
		</tr>
	</xsl:template>
</xsl:stylesheet>