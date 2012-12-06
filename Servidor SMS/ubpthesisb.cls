%% ========================================================================
%% Admin
%% ========================================================================
\def\fileversion{0.1beta}
\def\filedate{2005/11/21}
\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{ubpthesisb}[\filedate\space UBP thesis class by Sergio Schvezov (v\fileversion)]
%\typeout{hepthesis v\fileversion{} by Andy Buckley}


\LoadClass[11pt,a4paper,oneside]{book}

%% Funky headers
\RequirePackage{fancyhdr}
%% Verbatim things
\RequirePackage{cite}
\RequirePackage{apalike}
\RequirePackage{graphicx}
\RequirePackage{amsmath}
%% ========================================================================
%% Headers and footers
%% ========================================================================
%% No header or footer on the title page
\AtBeginDocument{\thispagestyle{plain}}
%% Use funky headers and footers
\pagestyle{fancy}

%% Normal headers and footers (headers are all like RH pages for oneside)
\ifx\@oneside\@empty%
  \fancyhead[RO,LE]{\bfseries\thepage}%
  \fancyhead[LO,RE]{\bfseries\leftmark}%
\else%
  \fancyhead[RO,R]{\bfseries\thepage}%
  \fancyhead[LO,L]{\bfseries\leftmark}%
\fi%
\fancyfoot{}%
%% Lower case header content
\renewcommand{\chaptermark}[1]{%
  \markboth{#1}{}%
}

\def\director#1{\gdef\@director{#1}}
%\def\asesor#1{\gdef\@asesor{#1}}

\renewcommand{\maketitle}{%
\begin{titlepage}%
  \let\footnotesize\small
  \let\footnoterule\relax
  \let \footnote \thanks
  \null\vfil
  %\vskip 60\p@
  \begin{center}%
  	{\LARGE UNIVERSIDAD CATOLICA ARGENTINA}
    \vskip 3em%
    {\LARGE \textsc{Facultad de Ingenier\'ia}}
    \vskip 2em
    \includegraphics[width=0.30\textwidth]{images/LogoUCA}
    \vskip 1em
    {\LARGE \textsc{Trabajo Final de la Carrera de Especializaci\'on en Ingenier\'ia de Software } \par}
    \vskip 3em
    {\huge \@title \par}%
    \vskip 3em%
  \end{center}\par
    
    \lineskip .75em%
    \textsc{
    	\begin{tabular}[t]{ll}%
      	\Large{Autor:} & \Large{\@author}
    		\vspace*{1cm} \\
      	Director: & \@director\\
%      	Asesor: & \@asesor\\
    	\end{tabular}
    }\par%
  
  
%  {\Large
%    \lineskip .75em%
%    \textsc{Autor:
%    \begin{tabular}[t]{l}%
%      \@author
%    \end{tabular}}\par%
%  }
%  {\large
%  \lineskip 5em%
%    \textsc{Director: \@director} \par%
%  }
%  {\large
%  \lineskip .65em%
%    \textsc{Asesor: \@asesor} \par%
%  }

  \vskip 5em%
  {\center \Huge \@date \par}%       % Set date in \large size.
  
  \@thanks
  \vfil\null
  \end{titlepage}%
  \setcounter{footnote}{0}%
  \global\let\thanks\relax
  \global\let\maketitle\relax
  \global\let\@thanks\@empty
  \global\let\@author\@empty
  \global\let\@date\@empty
  \global\let\@title\@empty
  \global\let\@director\@empty
  \global\let\title\relax
  \global\let\author\relax
  \global\let\date\relax
  \global\let\and\relax
  \global\let\@director\relax
}

%% Environment for acknowledgements page in frontmatter
\newenvironment{acknowledgements}{%
  \setlength{\textwidth}{2em}%
  \cleardoublepage%
  \vspace*{1cm}%
  \begin{center}%
    {\Huge{\textbf{Agradecimientos}}}%
  \end{center}%
  \vspace*{1cm}%
}{%
%  \setlength{\textwidth}{\@maintextwidth}%
  \ignorespacesafterend%
}

%% Environment for dedications page in frontmatter
\newenvironment{dedicate}{%
  %\raggedright%
  \setlength{\textwidth}{2em}%
  \cleardoublepage%
  \vspace*{1cm}%
  \begin{center}%
    {\Huge{\textbf{Dedicatoria}}}%
  \end{center}%
  \vspace*{1cm}%
}{%
%  \setlength{\textwidth}{\@maintextwidth}%
  \ignorespacesafterend%
}

\newenvironment{resumen}{%
  %\raggedright%
  \setlength{\textwidth}{2em}%
  \cleardoublepage%
  \vspace*{1cm}%
  \begin{center}%
    {\Huge{\textbf{Resumen}}}%
  \end{center}%
  \vspace*{1cm}%
}{%
%  \setlength{\textwidth}{\@maintextwidth}%
  \ignorespacesafterend%
}

%% Environment for preface page in frontmatter
\newenvironment{preface}{%
  \raggedright%
  \ifnum \c@secnumdepth > \m@ne \Huge\bf 
  \setlength{\textwidth}{\@frontmattertextwidth}%
  \cleardoublepage%
  \vspace*{\@frontmattertopskip}%
  \begin{center}%
    {\Huge{\textbf{Preface}}}%
  \end{center}%
  \vspace*{1cm}%
}{%
  \setlength{\textwidth}{\@maintextwidth}%
  \ignorespacesafterend %
}

%Para que diga tabla en vez de cuadro
\renewcommand\listoftables{%
    \if@twocolumn
      \@restonecoltrue\onecolumn
    \else
      \@restonecolfalse
    \fi
    \chapter*{\listtablename}%
      \@mkboth{%
          \MakeUppercase{\'Indice de tablas}}%
         {\MakeUppercase{\'Indice de tablas}}%
    \@starttoc{lot}%
    \if@restonecol\twocolumn\fi
    }
\renewcommand{\tablename}{Tabla}
\renewcommand{\listtablename}{\'Indice de tablas}

%Para que no diga capitulo
\def\@makechapterhead#1{ \vspace*{50pt} { \parindent 0pt
    \raggedright%
    \ifnum \c@secnumdepth > \m@ne \Huge\bf 
    \thechapter. \hskip 0.5em \fi \Huge \bf #1\par
    \nobreak \vskip 40pt } }
\def\@makeschapterhead#1{ \vspace*{50pt} { \parindent 0pt \raggedright
    \Huge \bf #1\par
    \nobreak \vskip 40pt }} 

%para que no diga parte
\def\@part[#1]#2{%
    \ifnum \c@secnumdepth >-2\relax
      \refstepcounter{part}%
      \addcontentsline{toc}{part}{\Large#1}%
    \else
      \addcontentsline{toc}{part}{\Large#1}%
    \fi
    \markboth{}{}%
    {\centering
     \interlinepenalty \@M
     \normalfont
%Agregar para que numere las partes
%     \ifnum \c@secnumdepth >-2\relax
%       \huge\bfseries \nobreakspace\thepart
%       \par
%       \vskip 20\p@
%     \fi
     \Huge \bfseries #2\par}%
 		 \vspace*{1cm}%
%    \@endpart}
}

\endinput
