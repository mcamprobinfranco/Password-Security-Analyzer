const API_URL = 'http://localhost:8080/api/passwords';

export async function analyzePassword(password) {
  const response = await fetch(`${API_URL}/analyze`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ password }),
  });

  if (!response.ok) {
    throw new Error('Error al analizar la contraseña');
  }

  return response.json();
}