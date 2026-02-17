import "../../styles/main.css";

interface Place {
    continent: string;
    name: string;
    city: string;
    rating: number;
    reviews: number;
}

interface Continent {
    name: string;
    img: string;
    places: Place[];
}

const continents: Continent[] = [
    {
        name: "Asia",
        img: "test1.jpg",
        places: [
            { continent: "Asia", name: "상하이 디즈니랜드", city: "상하이", rating: 4.7, reviews: 288963 },
            // 추가 아시아 장소 url(../../public/test1.jpg);
        ],
    },
    {
        name: "Europe",
        img: "test2.jpg",
        places: [
            { continent: "Europe", name: "에든버러 캐슬", city: "에든버러", rating: 4.7, reviews: 2536 },
            // 추가 유럽 장소
        ],
    },
    {
        name: "America",
        img: "test3.jpg",
        places: [
            { continent: "America", name: "자유의 여신상", city: "뉴욕", rating: 4.6, reviews: 45123 },
        ],
    },
    {
        name: "Africa",
        img: "test4.jpg",
        places: [
            { continent: "Africa", name: "피라미드", city: "카이로", rating: 4.9, reviews: 8923 },
        ],
    },
    {
        name: "Oceania",
        img: "test5.jpg",
        places: [
            { continent: "Oceania", name: "오페라 하우스", city: "시드니", rating: 4.8, reviews: 2345 },
        ],
    },
];

const PlaceCard = ({ place }: { place: Place }) => (
    <div className="place-card">
        {/*<img src={place.img} alt={place.name} />*/}
        <div className="info">
            <h1>{place.continent}</h1>
            <p>{place.name}</p>
            <p>{place.city}</p>
            <p>⭐ {place.rating} / 리뷰 {place.reviews.toLocaleString()}</p>
        </div>
    </div>
);

// ✅ 대륙별 카드 그리드
const ContinentGrid = () => (
    <div className="continent-grid">
        {continents.map((continent) => (
            <div className="continent-card" key={continent.name} style={{
                backgroundImage: `url(${continent.img})`,
                backgroundSize: "cover",
                backgroundPosition: "center",
                backgroundRepeat: "no-repeat",
            }}>
                {/*continent-card에 img 영역 동적 그리기 <h3 className="continent-title">{continent.name}</h3>*/}
                <div className="places-grid">
                    {continent.places.map((place) => (
                        <PlaceCard key={place.name} place={place}/>
                    ))}
                </div>
            </div>
        ))}
    </div>
);

export default ContinentGrid;
