function SuggestionsList({ suggestions }) {
  if (!suggestions || suggestions.length === 0) {
    return <p className="all-good">✓ Sin sugerencias, tu contraseña luce bien</p>;
  }

  return (
    <div className="suggestions">
      <h3>Sugerencias de mejora</h3>
      <ul>
        {suggestions.map((s, i) => (
          <li key={i}>{s}</li>
        ))}
      </ul>
    </div>
  );
}

export default SuggestionsList;