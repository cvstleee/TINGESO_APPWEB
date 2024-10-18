import React from "react";
import "./Navbar.css";

function Navbar() {
    return (
        <div className="Navbar">
            <nav className="navbar bg-body-tertiary">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">
                        <img 
                            src="src//assets/banco.png" 
                            alt="Logo" 
                            width="50" 
                            height="40" 
                            className="d-inline-block align-text-top" 
                        />
                        Presta Banco
                    </a>
                </div>
            </nav>
        </div>
    );
}

export default Navbar;