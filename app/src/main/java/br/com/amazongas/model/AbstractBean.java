package br.com.amazongas.model;

import java.io.Serializable;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected long id;
/*
	public AbstractBean() {
		id = 0; // Valor nulo para o id de uma nova instância
	}
*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractBean other = (AbstractBean) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
